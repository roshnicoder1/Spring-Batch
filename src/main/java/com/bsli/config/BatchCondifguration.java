package com.bsli.config;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.bsli.listener.HwJobExecutionListener;
import com.bsli.listener.HwStepExecutionListener;
import com.bsli.model.Employee;
import com.bsli.model.Product;
import com.bsli.processor.DataExtractionProcessor;
import com.bsli.reader.DataExtractionReader;
import com.bsli.writer.DataExtractionWriter;

@EnableBatchProcessing
@Configuration
public class BatchCondifguration {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private HwJobExecutionListener hwJobExecutionListener;

    @Autowired
    private HwStepExecutionListener hwStepExecutionListener;

    @Autowired
    private DataExtractionProcessor dataExtractionProcessor;

    public Tasklet helloWorldTasklet(){
        return (new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello world  " );
                return RepeatStatus.FINISHED;
            }
        });
    }
   
    
    //Reading from CSV product start
    @StepScope
    @Bean
    public FlatFileItemReader flatFileItemReader(){
        FlatFileItemReader reader = new FlatFileItemReader();
        // step 1 let reader know where is the file
        reader.setResource( new FileSystemResource("input/product1.csv")  );

        //create the line Mapper
        reader.setLineMapper(
                new DefaultLineMapper<Product>(){
                    {
                        setLineTokenizer( new DelimitedLineTokenizer() {
                            {
                                setNames( new String[]{"prodId","productName","prodDesc","price","unit"});
//                                setDelimiter("|");
                            }
                        });

                        setFieldSetMapper( new BeanWrapperFieldSetMapper<Product>(){
                            {
                                setTargetType(Product.class);
                            }
                        });
                    }
                }

        );
        //step 3 tell reader to skip the header
        reader.setLinesToSkip(1);
        return reader;

    }

    
    
    //reading from csv product end 
    
    //For Reading from CSV Files start
    @StepScope
    @Bean
    public FlatFileItemReader flatFileItemReader1(){
        FlatFileItemReader reader = new FlatFileItemReader();
        // step 1 let reader know where is the file
        reader.setResource( new FileSystemResource("input/employee.csv") );

        //create the line Mapper
        reader.setLineMapper(
                new DefaultLineMapper<Employee>(){
                    {
                        setLineTokenizer( new DelimitedLineTokenizer() {
                            {
                                setNames( new String[]{"Series_reference","Period","Data_value","Suppressed","STATUS","UNITS","Magnitude","Subject","Group","Series_title_1","Series_title_2","Series_title_3"});

//                                setDelimiter("|"); //if in csv File instead of , there is |
                            }
                        });

                        setFieldSetMapper( new BeanWrapperFieldSetMapper<Employee>(){
                            {
                                setTargetType(Employee.class);
                            }
                        });
                    }
                }

        );
        //step 3 tell reader to skip the header
        reader.setLinesToSkip(1);
        return reader;

    }

    
    
    //For Reading from csv files end
    
    //Reading from XML Start
    
    @StepScope
    @Bean
    public StaxEventItemReader xmlItemReader(){
        // where to read the xml file
        StaxEventItemReader reader = new StaxEventItemReader();
        reader.setResource(new FileSystemResource("input/product.xml") );
        //need to let reader to know which tags describe the domain object
        reader.setFragmentRootElementName("product");

        // tell reader how to parse XML and which domain object to be mapped
        reader.setUnmarshaller(new Jaxb2Marshaller(){
            {
                setClassesToBeBound(Product.class);
            }
        });

        return reader;

    }
    
    //Reading from XML End
//basic Step 1 start 
    @Bean
    public Step step1(){
        return steps.get("step1")
                .listener(hwStepExecutionListener)
                .tasklet(helloWorldTasklet())
                .build();
    }
  //basic Step 1 end 

    @Bean
    public DataExtractionReader reader(){
       return new DataExtractionReader();
    }


    //Defined step2 start Dedine reader,processor and writer start
    @Bean
    public Step step2(){
        return steps.get("step2").
                <Integer,Integer>chunk(3)
              //  .reader(reader())//Reading Dataextraction reader
               // .reader(flatFileItemReader()) //read from Product.csv
             //   .reader(flatFileItemReader1())
                .reader(xmlItemReader())//read from xml file
              //  .processor(dataExtractionProcessor)
                .writer(new DataExtractionWriter())
                .build();
    }
  //Defined step2 start Dedine reader,processor and writer end

    @Bean
    public Job helloWorldJob(){
        return jobs.get("helloWorldJob")
                .listener(hwJobExecutionListener)
                .start(step1())
                .next(step2())
                .build();
    }
}

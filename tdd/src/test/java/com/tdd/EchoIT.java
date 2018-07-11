package com.tdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EchoIT {

    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
    	Message message = new Message("Hi");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/echo/Hi",
        		Message.class)).isEqualTo(message);
    }
    
    /*
     * array length 5 
     * rotation 4
     * array 1 2 3 4 5
     * Expected 5 1 2 3 4
     */
    @Test
    public void leftRetotationTest1(){
    	int [] original_array = new int[]{1,2,3,4,5};
    	RotationRequest request = new RotationRequest();
    	request.setLength(5);
    	request.setRotation(4);
    	request.setArray(original_array);
    	assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/leftRotation",request,String.class))
    	.isEqualTo("5 1 2 3 4");
    }
    
    /*
     * array length 5 
     * rotation 4
     * array null
     * Expected empty string
     */
    @Test
    public void leftRetotationTest2(){
    	int [] original_array = null;
    	RotationRequest request = new RotationRequest();
    	request.setLength(5);
    	request.setRotation(4);
    	request.setArray(original_array);
    	assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/leftRotation",request,String.class))
    	.isEqualTo("empty");
    }
    
    /*
     * array length 0 
     * rotation 0
     * array null
     * Expected empty string
     */
    @Test
    public void leftRetotationTest3(){
    	RotationRequest request = new RotationRequest();
    	assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/leftRotation",request,String.class))
    	.isEqualTo("empty");
    }
    
    /*
     * array length 5
     * rotation 0
     * array [1 2 3 4 5]
     * Expected 1 2 3 4 5
     */
    @Test
    public void leftRetotationTest4(){
    	RotationRequest request = new RotationRequest();
    	request.setLength(5);
    	request.setRotation(0);
    	request.setArray(new int[]{1,2,3,4,5});
    	assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/leftRotation",request,String.class))
    	.isEqualTo("1 2 3 4 5");
    }
}
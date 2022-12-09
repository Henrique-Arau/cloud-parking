package one.digitalinnovation.parking.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

//https://cloud-pa.herokuapp.com/
@RestController
@RequestMapping("/")
@ApiIgnore
public class HelloController {

    @GetMapping
    public String hello(){
        return "Hello DIO... Java4" ;
    }
}

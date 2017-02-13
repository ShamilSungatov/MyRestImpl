package pro.shamil.provectus.simpleapp.service;


import pro.shamil.provectus.myrest.annotation.RestResource;

/**
 * Created by regal on 11.02.17.
 */
@RestResource
public class Greetings {
    public String helloWorld(){
        return "Hello";
    }

    public String helloUser(String name) {
        return "Hello " + name;
    }


}

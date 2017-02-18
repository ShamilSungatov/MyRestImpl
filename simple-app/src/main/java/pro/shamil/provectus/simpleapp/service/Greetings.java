package pro.shamil.provectus.simpleapp.service;


import jdk.nashorn.internal.objects.annotations.Getter;
import pro.shamil.provectus.myrest.annotation.Cache;
import pro.shamil.provectus.myrest.annotation.POST;
import pro.shamil.provectus.myrest.annotation.RestResource;

/**
 * Created by regal on 11.02.17.
 */
@RestResource
@RestResource(path = "hello")
@Cache
public class Greetings {
    @RestResource(path = "aa")
    @RestResource(path = "BB")
    public String helloWorld(){
        return "Hello";
    }

    @POST
    @RestResource
    public String helloUser(String name) {
        return "Hello " + name;
    }


}

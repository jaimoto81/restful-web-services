package com.jaimoto.rest.webservices.restfulwebservices.haloworld;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Tell spring is a controller
@RestController
public class HaloWorldController {

	//GET
	//URI  - /halo
	//@RequestMapping(method = RequestMethod.GET,path = "/halo")
	@GetMapping(path = "/halo")
	public String halo(){
		return "Halo";
	}

	//hello-world-bean
	@GetMapping(path = "/halo-bean")
	public HaloBean haloBean(){
		return new HaloBean("Halo world bean");
	}

	//hello-world-bean
	@GetMapping(path = "/halo-bean/var/{name}")
	public HaloBean haloBean(@PathVariable String name){
		return new HaloBean( String.format("Halo world bean %s",name));
	}

}

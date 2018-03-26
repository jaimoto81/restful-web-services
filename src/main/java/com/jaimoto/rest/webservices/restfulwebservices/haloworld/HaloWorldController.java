package com.jaimoto.rest.webservices.restfulwebservices.haloworld;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

//Tell spring is a controller
@RestController
public class HaloWorldController {

	@Autowired
	private MessageSource messageSource ;

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

	//hello-world-bean
	@GetMapping(path = "/halo-bean-i18n/var/{name}")
	public String haloBeanI18n(@PathVariable String name, @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
		String[] params = new String[1];
		params[0] = name;
		return  messageSource.getMessage("halo.message",params, locale);
	}

	//
    //hello-world-bean
    @GetMapping(path = "/halo-bean-i18n-2/var/{name}")
    public String haloBeanI18n(@PathVariable String name) {
        String[] params = new String[1];
        params[0] = name;
        //spring alternative from the requeste

        return  messageSource.getMessage("halo.message",params, LocaleContextHolder.getLocale());
    }

}

package pl.silverpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.silverpoint.domain.Service;
import pl.silverpoint.service.DataService;

@RestController
@RequestMapping(value= "/rest/service")
public class ServiceRestController {

	@Autowired
	private DataService dataService;
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public void create(@RequestBody Service newService) {
		dataService.addService(newService);
	}
	
	@ResponseBody
	@RequestMapping(value= "/{serviceId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Service read(@PathVariable(value="serviceId") long serviceId){
		return dataService.getServiceById(serviceId);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update (@RequestBody Service service){
		dataService.updateService(service);
	}
	
	@ResponseBody
	@RequestMapping(value="/{serviceId}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("serviceId") long serviceId){
		dataService.deleteService(serviceId);
	}
	
}

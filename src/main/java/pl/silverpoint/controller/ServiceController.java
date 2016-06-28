package pl.silverpoint.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.silverpoint.domain.Service;
import pl.silverpoint.domain.User;
import pl.silverpoint.exception.ServiceNotFoundException;
import pl.silverpoint.service.DataService;
import pl.silverpoint.service.UserService;

@Controller
@RequestMapping(value = "/service")
public class ServiceController {

	@Autowired
	private DataService dataService;

	@Autowired
	private UserService userService;

	@RequestMapping
	public String list(Model model) {
		model.addAttribute("serviceList", dataService.list());
		return "service/service";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addService(ModelMap modelMap, Principal principal) {
		Service service = new Service();
		List<User> employeesList = userService.employeesList();
		modelMap.addAttribute("newService", service);
		modelMap.addAttribute("adderUserName", principal.getName());
		modelMap.addAttribute("employees", employeesList);
		return "service/addService";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewServiceForm(@ModelAttribute("newService") @Valid Service newService, BindingResult result) {
		if (result.hasErrors()) {
			return "service/addService";
		}
		newService.setStartDate(dataService.getTodayDate());
		if (newService.getStatus().equals("done")) {
			newService.setFinishDate(dataService.getTodayDate());
		}
		dataService.addService(newService);
		return "redirect:/service";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editService(@RequestParam("id") long serviceId, ModelMap modelMap) {
		Service serviceToEdit = dataService.getServiceById(serviceId);
		List<User> employeesList = userService.employeesList();
		modelMap.addAttribute("editService", serviceToEdit);
		modelMap.addAttribute("employees", employeesList);
		return "service/editService";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String updateService(@ModelAttribute("editService") Service editedService) {
		if (editedService.getStatus().equals("done")) {
			editedService.setFinishDate(dataService.getTodayDate());
		}
		dataService.updateService(editedService);
		return "redirect:/service";
	}

	@RequestMapping(value = "/delete")
	public String deleteService(@RequestParam("id") long serviceId) {
		Service serviceToDelete = dataService.getServiceById(serviceId);
		dataService.deleteService(serviceToDelete.getServiceId());
		return "redirect:/service";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		formater.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(formater, true));
	}
	
	@ExceptionHandler(ServiceNotFoundException.class)
	public String handleError(Model model, ServiceNotFoundException exception){
		model.addAttribute("invalidServiceId", exception.getServiceId());
		return "service/serviceNotFound";
	}
}

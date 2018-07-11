package com.tdd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tdd.service.TddService;

@Controller
public class TddController {
	
	@Autowired
	private TddService tddService;

	@RequestMapping(path = "/echo/{message}", method= RequestMethod.GET)
	public @ResponseBody Message echo(@PathVariable("message") String message) {
		return new Message(message);
	}
	
	@RequestMapping(value = "/leftRotation",method= RequestMethod.POST)
	public @ResponseBody String leftRotation(@RequestBody RotationRequest rotationRequest ) {
		return tddService.leftRotation(rotationRequest);
	}
	
	@RequestMapping(path = "/hack/{pool}/{loop}", method= RequestMethod.GET)
	public @ResponseBody Message hack(@PathVariable("pool") int pool,@PathVariable("loop") int loop) {
		tddService.executeHack(pool,loop);
		return new Message(pool + "");
	}
}

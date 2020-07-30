package com.stackroute.auth.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.auth.model.User;
import com.stackroute.auth.service.UserInter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/ustg/authenticate")
@CrossOrigin
public class UserController {
	
	@Autowired
	UserInter userserve;
	
	@PostMapping("/saveuser")
	public ResponseEntity saveuser(@RequestBody User user)
	{
		User uboj=userserve.saveUser(user);
return new ResponseEntity<User>(uboj,HttpStatus.OK);		
		
	}
	
	@PostMapping("/login")
	public ResponseEntity loginUser(@RequestBody User user)
	{
		
		boolean resul=userserve.loginUser(user.getUserid(), user.getUsername());
		if (resul)
		{
			
		  String tok=generateToke(user.getUserid(),user.getUsername());
		  HashMap<String,String> map=new HashMap();
		  map.put("token",tok);
		return new ResponseEntity<HashMap>(map,HttpStatus.ACCEPTED);		  
		}
		else
			return new ResponseEntity<String>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
	}


	public String generateToke(String uid,String uname)
	{
		long expireduration=10_00_0000;
		
		return Jwts.builder().setSubject(uname)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+expireduration))
				.signWith(SignatureAlgorithm.HS256, "ustkey").compact();
				
				
		
		
	}

	


}




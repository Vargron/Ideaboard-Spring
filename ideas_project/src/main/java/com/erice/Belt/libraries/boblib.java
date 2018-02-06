package com.erice.Belt.libraries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
//about boblib bob lib is a rebellion against springs unwieldy built ins 
// why do i need to know how 
public class boblib {
	//converts a string from html to date object
	public static Date htmltodate(String mystr){
		String ystr="";
		String mstr="";
		String dstr="";
		for (int i=0; i<mystr.length();i++) {
			if(i>-1 &&i<4) {
				ystr=ystr+Character.toString(mystr.charAt(i));
			}else if(i>4 &&i<7){
				mstr=mstr+Character.toString(mystr.charAt(i));
				
			}else if(i>7) {
				dstr=dstr+Character.toString(mystr.charAt(i));
			}
		}

		Date mydate=new Date();
		mydate.setMonth(Integer.parseInt(mstr));
		mydate.setYear(Integer.parseInt(ystr));
		mydate.setDate(Integer.parseInt(dstr));
		mydate.setHours(12);
		mydate.setMinutes(0);
		mydate.setSeconds(0);
		
		
		return mydate;
	}
	// takes a input and checks it for spaces returns true if no spaces
	public static boolean checkspaces(String string) {
		for (int i=0;i<string.length();i++) {
			if (string.charAt(i)==' ') {
				
				return false;
			}
		}
		return true;
	}
	
	// checks a string to see if it's a valid email
	public static boolean checkemail(String email) {
		int dot=0;
		int at=0;
		int order=0;
		for (int i=0; i<email.length();i++) {
			if (email.charAt(i)=='@') {
				at+=1;
				
			}else if(email.charAt(i)=='.'){
				dot+=1;
				if (at==1) {
					order+=1;
				}
			}
			else if(email.charAt(i)==' '){
				return false;
				
			}
			
		}
		return((dot>0 &&at==1&&order>0));
	}
// turns an unweildy error object into somthing useful only requires the result object
	public static List<String> resulterrors(BindingResult result){
		List<String>myerrors=new ArrayList();
		List<ObjectError>syserrors=result.getAllErrors();
		for (int i=0; i <syserrors.size();i++) {
			myerrors.add(syserrors.get(i).getDefaultMessage());
		}
		return myerrors;
				
	}
//same as above but allows you to append on to your already existing arraylist
	public static List<String> resulterrors(BindingResult result,List<String>myerrors ){
		
		List<ObjectError>syserrors=result.getAllErrors();
		for (int i=0; i <syserrors.size();i++) {
			myerrors.add(syserrors.get(i).getDefaultMessage());
		}
		return myerrors;
				
	}
	public static Boolean checkloggedin(HttpSession session) {
		if(session.getAttribute("user")==null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	
		

}

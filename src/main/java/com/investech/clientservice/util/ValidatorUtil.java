package com.investech.clientservice.util;

import com.investech.clientservice.exception.ClientException;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

  public static boolean checkSAIdNumber(String idNumber){

    if(idNumber.length()!=13){
      throw new ClientException("Id number provided does not match the correct length of an SA ID number: "+ idNumber);
    }

    //TODO check any other checks that can be used to check the validity of an ID number

   return checkLuhn(idNumber);

  }

  public static boolean isValidPhoneNumber(String mobileNumber)
  {
     //TODO check other mobile number with 05 or 09
    // The given argument to compile() method
    // is regular expression. With the help of
    // regular expression we can validate mobile
    // number.
    // 1) Begins with 0 or 27
    // 2) Then contains 6 or 7 or 8.
    // 3) Then contains 8 digits
    Pattern p = Pattern.compile("(0|27)?[6-8][0-9]{8}");

    Matcher m = p.matcher(mobileNumber);
    return (m.find() && m.group().equals(mobileNumber));
  }

  public static Boolean checkLuhn(String identities){
    char[] idchars = identities.toCharArray();
    int sum = 0;
    // loop over each digit right-to-left, including the check-digit
    for (int i = 1; i <= idchars.length; i++) {
      int digit = Character.getNumericValue(idchars[idchars.length - i]);
      if ((i % 2) != 0) {
        sum += digit;
      } else {
        sum += digit < 5 ? digit * 2 : digit * 2 - 9;
      }
    }
    return (sum % 10) == 0;
  }

}

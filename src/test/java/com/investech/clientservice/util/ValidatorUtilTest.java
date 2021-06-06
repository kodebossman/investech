package com.investech.clientservice.util;

import com.investech.clientservice.exception.ClientException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidatorUtilTest {

  private static final String correctID = "2103015800089";
  private static final String inCorrectID = "8708294668996";
  private static final String inCorrectLengthID = "2103019900000000";
  private static final String inCorrectShortLengthID = "2103019900000000";


  @Test
  void checkSAIdNumberTest() {

    Assertions.assertTrue(ValidatorUtil.checkSAIdNumber(correctID));

    //check ID length exception
    ClientException exception = Assertions.assertThrows(ClientException.class, () -> {
      ValidatorUtil.checkSAIdNumber(inCorrectLengthID);
    });

    //check if the exception thrown is the actual exception
    String expectedMessage = "Id number provided does not match the correct length of an SA ID number: " + inCorrectLengthID;
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));

  }

  @Test
  void checkSAIdNumberInCorrectLengthIDTest() {

    //check ID length exception
    ClientException exception = Assertions.assertThrows(ClientException.class, () -> {
      ValidatorUtil.checkSAIdNumber(inCorrectShortLengthID);
    });

    //check if the exception thrown is the actual exception
    String expectedMessage = "Id number provided does not match the correct length of an SA ID number: " + inCorrectLengthID;
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void checkSAIdNumberInCorrectIDTest() {
    Assertions.assertFalse(ValidatorUtil.checkSAIdNumber(inCorrectID));
  }

  @Test
  void isValidPhoneNumberTest() {

    Assertions.assertFalse(ValidatorUtil.isValidPhoneNumber("0577756599"));
    Assertions.assertTrue(ValidatorUtil.isValidPhoneNumber("0777756599"));
    Assertions.assertTrue(ValidatorUtil.isValidPhoneNumber("27777756599"));
    Assertions.assertFalse(ValidatorUtil.isValidPhoneNumber("263777756599"));
  }
  // boundary value analysis on Luhn Check
  @Test
  void checkLuhnTest() {

    Assertions.assertTrue(ValidatorUtil.checkLuhn(correctID));
    Assertions.assertFalse(ValidatorUtil.checkLuhn(inCorrectID));
    Assertions.assertFalse(ValidatorUtil.checkLuhn(inCorrectLengthID));
    Assertions.assertFalse(ValidatorUtil.checkLuhn(inCorrectShortLengthID));
  }
}
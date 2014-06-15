function IsNumeric(sText)
{
   var ValidChars = "0123456789";
   var IsNumber=true;
   var Char;
   var i;
   
   for (i = 0; i < sText.length && IsNumber == true; i++) 
      { 
      Char = sText.charAt(i); 
      if (ValidChars.indexOf(Char) == -1) 
         {
         IsNumber = false;
         }
      }
   return IsNumber;
   
}
CSV to XML Converter

Assumptions:
1. CSV file first line is the headers.
	example: Name,Phone,Address
2. Following lines are individual records
	example: ABC,12345,Comm St
3. Currently Main class calls convertToXML function with input and output file name.
	Input file sample.csv is present in src folder.
	Output file finalXML.xml generated in project. 
	If file not visible in project explorer, please refresh.

Dependencies:
All dependencies can be found in the project.
This project was written on a MacOS machine on intelliJ primarily, but then migrated to Eclipse for easy distribution.

Class Description:
Main - Driver class for project. Calls convertToXML function with i/p and o/p file name.

Converter - Converts the input csv to xml. 
The convertToXML method returns a boolean for file conversion.
If file conversion unsuccessful, Error is displayed.

ConverterTest - JUnit test cases for Converter.
1. testConvertToXML()
	- checks successful conversion of correct csv file to xml.
	- generated file compared to expected file using compareFiles function.
	- compareFile utility compares files by converting them to string and hashing.
2. testFileFormatWrong()
	- Checks for wrong file format files.
	- Error is displayed and file conversion Aborted.
3. testFileIsEmpty()
	- Checks for empty file.
	- Error is displayed and file conversion Aborted.
4. testFileDoesNotExist()
	- Checks for file that does not exist.
	- Error is displayed and file conversion Aborted.
5. testHeaderNoValue()
	- Checks for file where no matching value is found for a tag.
	- Warning displayed and NA added to tag.
6. testValueNoHeader()
	- Checks for file where a record has more values than tag names.
	- Warning displayed and extra values are not added to xml.

Future Version
1. Allow user to pick input file from a browser like utility or Paste CSV as text. 
2. Allow user to enter output file name and select location when file conversion complete.
3. XSLT to make XML better aligned.
4. Allow user to select splitter.(',','#',':')
5. Allow user to sort which header displays first in XML.
	(example: Name,Address,ID) become 
	<ID></ID>
	<Name></Name>
	<Address></Address>
6. Allow user to skip certain values in csv 
	(example: Name,Address,Phone) user can skip Address
7. Allow user to decide what to do with empty values.

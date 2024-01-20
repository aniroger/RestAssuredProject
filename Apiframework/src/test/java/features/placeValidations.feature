Feature: Validating Place API's


  @tag1
  Scenario Outline: Verifying if place is being added successfully
    Given Add Place payload with "<name>"  "<language>" "<address>"
    When user calls "AddPlaceApi" with "POST" http request
  	Then the API call is success with status code 200
  	#And "status" in response body is "OK"
    #And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceApi"
    
    Examples:
    		   |name | language | address|
    		   |raj  | Hindi    | abcde  |
    		   |rohan| Telgu		| ghik	 |	
    		   
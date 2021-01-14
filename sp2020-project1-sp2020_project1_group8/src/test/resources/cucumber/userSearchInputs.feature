Feature: Vacation Planning Search Input
  Scenario: Open hello page from index page
		Given I am on the 'Vacation Planning' page
		When I fill all vacation parameters correctly
		And I click the button 'findVacation'
		Then I see no error

	Scenario: Illegal Temperature Range
		Given I am on the 'Vacation Planning' page
		When I fill all vacation parameters correctly
		And I input 'tempLow' with '60'
		And I input 'tempHigh' with '50'
		And I click the button 'findVacation'
		Then I see error for 'tempHigh'
   
  Scenario: Empty Low Temperature
		Given I am on the 'Vacation Planning' page
		When I fill all vacation parameters correctly
		And I input 'tempLow' with ''
		And I click the button 'findVacation'
		Then I see error for 'tempLow'
	   
	Scenario: Empty High Temperature
		Given I am on the 'Vacation Planning' page
	  When I fill all vacation parameters correctly
	  And I input 'tempHigh' with ''
	  And I click the button 'findVacation'
	  Then I see error for 'tempHigh'
	  
  Scenario: Illegal Number of Results
	  Given I am on the 'Vacation Planning' page
	  When I fill all vacation parameters correctly
	  And I input 'numParams' with '-1'
	  And I click the button 'findVacation'
	  Then I see error for 'numParams'
	  
  Scenario: Empty Number of Results
	  Given I am on the 'Vacation Planning' page
	  When I fill all vacation parameters correctly
	  And I input 'numParams' with ''
	  And I click the button 'findVacation'
	  Then I see error for 'numParams'
	  
  Scenario: Illegal Location
	  Given I am on the 'Vacation Planning' page
	  When I fill all vacation parameters correctly
	  And I input 'loc' with '100'
	  And I click the button 'findVacation'
	  Then I see error for 'loc'
	  
  Scenario: Empty Location
	  Given I am on the 'Vacation Planning' page
	  When I fill all vacation parameters correctly
	  And I input 'loc' with ''
	  And I click the button 'findVacation'
	  Then I see error for 'loc' 
	   
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Grade Store</title>
		<link href="https://selab.hanyang.ac.kr/courses/cse326/2019/labs/labResources/gradestore.css" type="text/css" rel="stylesheet" />
	</head>

	<body>
		
		<?php
		# Ex 4 : 
		# Check the existence of each parameter using the PHP function 'isset'.
		# Check the blankness of an element in $_POST by comparing it to the empty string.
		# (can also use the element itself as a Boolean test!)
		# if (){
		?>

		<!-- Ex 4 : 
			Display the below error message : 
			<h1>Sorry</h1>
			<p>You didn't fill out the form completely. Try again?</p>
		--> 

		<?php
		# Ex 5 : 
		# Check if the name is composed of alphabets, dash(-), ora single white space.
		# } elseif () { 
		?>

		<!-- Ex 5 : 
			Display the below error message : 
			<h1>Sorry</h1>
			<p>You didn't provide a valid name. Try again?</p>
		--> 

		<?php
		# Ex 5 : 
		# Check if the credit card number is composed of exactly 16 digits.
		# Check if the Visa card starts with 4 and MasterCard starts with 5. 
		# } elseif () {
		?>

		<!-- Ex 5 : 
			Display the below error message : 
			<h1>Sorry</h1>
			<p>You didn't provide a valid credit card number. Try again?</p>
		--> 

		<?php
		# if all the validation and check are passed 
		# } else {
		?>


		
		<!-- Ex 2: display submitted data -->
		<?php 
			if(isset($_POST['name'])&isset($_POST['id'])&isset($_POST['check'])&
				isset($_POST['grade'])&isset($_POST['cardnumber'])&isset($_POST['card'])){
				$name = $_POST['name'];
				$id = $_POST['id'];
				if (isset($_POST['check']))
					$check = implode(',',$_POST['check']);
				$grade = $_POST['grade'];
				$cardnumber = $_POST['cardnumber'];
				$card = $_POST['card'];

			if( (is_numeric($_POST['cardnumber'])==false)|| (strlen($_POST['cardnumber'])!=16) ){
		?>
			<h1>Sorry</h1>
			<p>You didn't provide a valid credit card number. <a href="./gradestore.html">Try agian?</a> </p>
		<?php
			}
			else if(is_numeric($_POST['name'])){
		?>
			<h1>Sorry</h1>
			<p>You didn't provide a valid name. <a href="./gradestore.html">Try agian?</a> </p>
		<?php		
			}
			else{
		?>
		<h1>Thanks, looser!</h1>
		<p>Your information has been recorded.</p>

		<ul> 
			<li>Name: <?= $name ?></li>
			<li>ID: <?= $id ?></li>
			<!-- use the 'processCheckbox' function to display selected courses -->
			<li>Course: <?= $check ?></li>
			<li>Grade: <?= $grade ?></li>
			<li>Credit Card: <?= $cardnumber?>  (<?= $card ?>)</li>
		</ul>
		
		<!-- Ex 3 : 
			<p>Here are all the loosers who have submitted here:</p> -->
			<?php  file_put_contents('loosers.txt',$name.';'.$id.';'.$cardnumber.';'.$card.'<br/>',FILE_APPEND)
			?>
			<pre><?= file_get_contents('loosers.txt'); ?></pre>
		<?php
			$filename = "loosers.txt";
			}
		}
			
			else{
		?>
			<h1>Sorry</h1>
		<p>You didn't fill out the form completely. <a href="./gradestore.html">Try agian?</a> </p>
		<?php   }
			/* Ex 3: 
			 * Save the submitted data to the file 'loosers.txt' in the format of : "name;id;cardnumber;cardtype".
			 * For example, "Scott Lee;20110115238;4300523877775238;visa"
			 */
		?>

		
		<!-- Ex 3: Show the complete contents of "loosers.txt".
			 Place the file contents into an HTML <pre> element to preserve whitespace -->

		
		<?php
		# }
		?>
		
		<?php
			/* Ex 2: 
			 * Assume that the argument to this function is array of names for the checkboxes ("cse326", "cse107", "cse603", "cin870")
			 * 
			 * The function checks whether the checkbox is selected or not and 
			 * collects all the selected checkboxes into a single string with comma separation.
			 * For example, "cse326, cse603, cin870"
			 */
			function processCheckbox($names){ }
		?>
		
	</body>
</html>

function forgotPassword(username,newpassword){
	var Properties = Java.type("java.util.Properties");
    var Driver = Java.type("com.mysql.cj.jdbc.Driver");
    var driver = new Driver();
    
    var properties = new Properties();
    var con = null;
    try {
	print("In javascript code ,for forget password");
        properties.setProperty("user", "root");
        properties.setProperty("password", "pratik@7030");
        con = driver.connect("jdbc:mysql://localhost:3306/tweetapp", properties);
		var query="SELECT * from userdetails where username=\'"+username+"\'";
		var stmt = con.prepareStatement(query);
       	var resultSet = stmt.executeQuery();
		if(resultSet.next()){
			var sql = "UPDATE userdetails set password=\'"+newpassword+"\' where username=\'"+username+"\'";
			var stmt2 =con.prepareStatement(sql);
			stmt2.execute();
			print("Success");
			return true;
		}
		return false;

	}
	catch(e) {
		print(e);
		return false;
	}
	finally{
		con.close();
	}
}
db = connect("localhost:27017/sample");

db.createUser({user: "hisyam", pwd: "P@ssw0rd", roles : [{role: "readWrite", db: "sample"}]});
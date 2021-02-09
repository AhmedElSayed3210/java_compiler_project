# convert josn file to java pojo
convert josn file to java class using compiler parser
create a java class 
you dont need to creat the pojo because it's created automatic


#for Example

#Json File Date:
{
"id":5,
"age":22,
"Name":"Ahmed",
"address":"Zagazig",
"phone":["test","test1"]

}


#Java Pojo Code:

import java.util.ArrayList;

public class json_To_java {

private int id;
private int age;
private String Name;
private String address;
private ArrayList phone;

public void setId(int Id) {
  this.Id = Id;
 }
 public int getId() {
  return Id;
 }

public void setAge(int Age) {
  this.Age = Age;
 }
 public int getAge() {
  return Age;
 }

public void setName(String Name) {
  this.Name = Name;
 }
 public String getName() {
  return Name;
 }

public void setAddress(String Address) {
  this.Address = Address;
 }
 public String getAddress() {
  return Address;
 }

public void setPhone(ArrayList Phone) {
  this.Phone = Phone;
 }
 public ArrayList getPhone() {
  return Phone;
 }

}

package br.com.gustavobalula.todolist.user;

public class UserModel {
    
   public String username;
   public String name;
   public String password;


   //getters setters
   
   // Username
   public void setUsername(String username) {
       this.username = username;
   }

   public String getUsername() {
       return username;
   }

   // Name
   public void setName(String name) {
       this.name = name;
   }

   public String getName() {
       return name;
   }
   

   // Password
   public void setPassword(String password) {
       this.password = password;
   }

   public String getPassword() {
       return password;
   }
}

export interface User {
  userId : number;
  email : string;
  password : string;
  hrVerified : boolean;
  passwordChanged : boolean;
  dateCreated : Date;
  lastLogin : Date;
}

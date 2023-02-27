export class User {
  userId : number;
  email : string;
  password : string;
  hrVerified : boolean;
  passwordChanged : boolean;
  dateCreated : Date;
  lastLogin : Date;
  accountType : string;

  constructor() {
    this.userId = 0;
    this.email = "";
    this.password = "";
    this.hrVerified = false;
    this.passwordChanged = false;
    this.dateCreated = new Date;
    this.lastLogin = new Date;
    this,this.accountType = "";
  }
}

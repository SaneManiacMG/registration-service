import { User } from 'src/app/model/user.model';

export class ClientResponse {
  message: string;
  user: User;

  constructor() {
    this.message = "";
    this.user = new User();
  }
}


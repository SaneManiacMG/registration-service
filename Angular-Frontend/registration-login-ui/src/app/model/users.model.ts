export interface Users {
    userId: number;
    email: string;
    password: string;
    isHrVerified: boolean;
    isPasswordChanged: boolean;
    accountCreated: Date;
    lastLogin: Date;
}

import {type} from "./role";
import {Userprofile} from "./userprofile";

export class User {
    firstname!: string;
    lastname!: string;
    mobile!:string;
    age!:string;
    gender!:string;
    username!:string;
    password!: string;
    type!: type;
    token?: string;
    email!: string


   Userprofile!: Userprofile;
}
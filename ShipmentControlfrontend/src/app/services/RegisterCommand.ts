export class RegisterCommand {

  private _email!:string;
  private _userType!:string;
  private _userName!:string;
  private _companyName!:string;
  private _telephoneNumber!:string;
  private _password!:string;
  private _confirmPassword!:string;

  get telephoneNumber(): string {
    return this._telephoneNumber;
  }

  set telephoneNumber(value: string) {
    this._telephoneNumber = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }

  get confirmPassword(): string {
    return this._confirmPassword;
  }

  set confirmPassword(value: string) {
    this._confirmPassword = value;
  }

  get userType(): string {
    return this._userType;
  }

  set userType(value: string) {
    this._userType = value;
  }

  get userName(): string {
    return this._userName;
  }

  set userName(value: string) {
    this._userName = value;
  }

  get companyName(): string {
    return this._companyName;
  }

  set companyName(value: string) {
    this._companyName = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }
}

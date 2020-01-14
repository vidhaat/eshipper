import { IClaimComment } from 'app/shared/model/claim-comment.model';

export interface IUser1 {
  id?: number;
  claimComments?: IClaimComment[];
}

export class User1 implements IUser1 {
  constructor(public id?: number, public claimComments?: IClaimComment[]) {}
}

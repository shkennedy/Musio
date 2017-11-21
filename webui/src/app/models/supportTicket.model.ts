import { User } from './user.model';

export class SupportTicket {
    public id: number;
    public assignedTo: number;
    public message: string;
    public status: number;
    public timestamp: Date;
    public user: User;
}

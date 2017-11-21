import { User } from './user.model.ts';

export class SupportTicket {
    public id: number;
    public assignedTo: number;
    public message: string;
    public status: byte;
    public timestamp: Date;
    public user: User;
}
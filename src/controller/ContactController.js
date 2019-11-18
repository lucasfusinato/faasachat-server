module.exports = new class {

    constructor() {
        this._contacts = [
            { userId: 1, contactId: 2 },
            { userId: 1, contactId: 3 },
            { userId: 1, contactId: 4 },
            { userId: 1, contactId: 5 },
            { userId: 2, contactId: 1 },
            { userId: 2, contactId: 3 },
            { userId: 3, contactId: 2 },
            { userId: 3, contactId: 4 },
            { userId: 4, contactId: 5 }
        ];
    }

    listAll(request, response) {
        const { userId } = request.params;
        this._notifySession(userId);
        return response.json({ error: false, content: this._contacts.filter(contact => contact.userId === parseInt(userId)).map(this._contactData.bind(this)) });
    }

    create(request, response) {
        const { userId } = request.params;
        const { id } = request.body;

        this._notifySession(userId);

        const contact = { userId: parseInt(userId), contactId: id };
        this._contacts.push(contact);

        return response.json({ error: false, content: contact })
    }

    delete(request, response) {
        const { userId, id } = request.params;
        
        this._notifySession(userId);

        let i = this._contacts.length - 1;
        while(i >= 0) {
            if(this._contacts[i].userId === parseInt(userId) && this._contacts[i].contactId === parseInt(id)) {
                this._contacts.splice(i, 1);
            }
            i--;
        }

        return response.json({ error: false, content: null });
    }

    _contactData(contact) {
        const UserController = require('./UserController');
        const SessionController = require('./SessionController');

        const nickname = UserController.getUserNickname(contact.contactId);
        const online = SessionController.getUserOnline(contact.contactId);
        return { id: contact.contactId, nickname, online };
    }

    _notifySession(userId) {
        const SessionController = require('./SessionController');
        SessionController.updateSession(userId);
    }

};
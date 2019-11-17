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
        return response.json({ error: false, content: this._contacts.filter(contact => contact.userId === parseInt(userId)).map(this._contactData.bind(this)) });
    }

    create(request, response) {
        const { userId } = request.params;
        const { id } = request.body;

        const contact = { userId, contactId: id };
        this._contacts.push(contact);

        return response.json({ error: false, content: contact })
    }

    delete(request, response) {
        const { userId, id } = request.params;

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
        const nickname = this._userRepository.getUserNickname(contact.contactId);
        const online = this._sessionRepository.getUserOnline(contact.contactId);
        return { nickname, online };
    }

};
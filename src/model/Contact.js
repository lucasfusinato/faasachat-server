class Contact {

    constructor() {
        this.contacts = [
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

    persist(contact) {
        const existingContact = this.getContactByIds(contact.userId, contact.contactId);
        if(!existingContact) {
            contact.userId = parseInt(contact.userId);
            contact.contactId = parseInt(contact.contactId);
            this.contacts.push(contact);
        }
    }

    getContactByIds(userId, contactId) {
        if(!userId) {
            return null;
        }
        if(!contactId) {
            return null;
        }
        userId = parseInt(userId);
        contactId = parseInt(contactId);
        return this.contacts.find(contact => contact.userId === userId && contact.contactId === contactId);
    }

    getContactsByUserId(userId) {
        if(!userId) {
            return null;
        }
        userId = parseInt(userId);
        return this.contacts.filter(contact => contact.userId === userId);
    }

}

module.exports = new Contact();
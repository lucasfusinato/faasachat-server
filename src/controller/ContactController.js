const Contact = require('../model/Contact');
const User = require('../model/User');
const Session = require('../model/Session');

class ContactController {

    constructor(contactModel, userModel, sessionModel) {
        this.contactModel = contactModel;
        this.sessionModel = sessionModel;
        this.userModel = userModel;
    }

    listAll(request, response) {
        const { userId } = request.params;
        const responseData = { error: false, content: null };

        try {
            this.sessionModel.updateSession(userId);
            
            responseData.content = this.contactModel.getContactsByUserId(userId).map(contact => {
                return {
                    id: contact.contactId,
                    nickname: this.userModel.getUserById(contact.contactId).nickname,
                    online: this.sessionModel.getOnlineAddress(contact.contactId)
                };
            });
        } catch(error) {
            responseData.error = true;
            responseData.content = error.message;
        }
        
        return response.json(responseData);
    }

    create(request, response) {
        const { userId } = request.params;
        const { nickname } = request.body;
        const responseData = { error: false, content: null };

        try {
            this.sessionModel.updateSession(userId);

            const userContact = this.userModel.getUserByNickname(nickname);
            if(!userContact) {
                throw new Error('User does not exists.');
            }
            if(this.contactModel.getContactByIds(userId, userContact.id)) {
                throw new Error('Contact already exists.');
            }

            this.contactModel.persist({ userId, contactId: userContact.id })
            responseData.content = this.userModel.getUserById(userContact.id);
        } catch(error) {
            responseData.error = true;
            responseData.content = error.message;
        }
        
        return response.json(responseData);
    }

    delete(request, response) {
        const { userId, contactId } = request.params;
        const responseData = { error: false, content: null };

        try {
            this.sessionModel.updateSession(userId);
            
            const contact = this.contactModel.getContactByIds(userId, contactId);
            if(!contact) {
                throw new Error('Contact does not exists.');
            }

            this.contactModel.delete(contact)
        } catch(error) {
            responseData.error = true;
            responseData.content = error.message;
        }

        return response.json({ error: false, content: null });
    }

}

module.exports = new ContactController(Contact, User, Session);
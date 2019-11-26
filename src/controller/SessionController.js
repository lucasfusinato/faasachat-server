const Session = require('../model/Session');
const User = require('../model/User');

class SessionController {

    constructor(sessionModel, userModel) {
        this.sessionModel = sessionModel;
        this.userModel = userModel;
    }

    create(request, response) {
        const { email, password, url } = request.body;
        const responseData = { error: false, content: null };
        
        try {
            const user = this.userModel.authenticate(email, password);
            const session = { userId: user.id, url, lastUpdate: Date.now(), online: true };

            this.sessionModel.persist(session);

            responseData.content = { session, user };
        } catch(error) {
            responseData.error = true;
            responseData.content = error.message;
        }

        return response.json(responseData);
    }

    delete(request, response) {
        const { sessionId } = request.params;
        
        const session = this.sessionModel.getSessionById(sessionId);
        if(session) {
            session.lastUpdate = Date.now();
            session.online = false;

            this.sessionModel.persist(session);
        }

        return response.json({ error: false });
    }
    
};

module.exports = new SessionController(Session, User);
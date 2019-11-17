module.exports = new class {

    constructor() {
        this._sessions = [
            { id: 'abcd1', userId: 1, lastUpdate: Date.now(), online: true, url: '' },
            { id: 'abcd2', userId: 2, lastUpdate: Date.now(), online: true, url: '' },
            { id: 'abcd3', userId: 3, lastUpdate: Date.now(), online: true, url: '' },
            { id: 'abcd4', userId: 4, lastUpdate: Date.now(), online: true, url: '' },
            { id: 'abcd5', userId: 5, lastUpdate: Date.now(), online: true, url: '' }
        ];
    }

    create(request, response) {
        const { userId, url } = request.body;
        const session = { id: this._generateSessionId(userId), userId, lastUpdate: Date.now(), online: true, url };

        this._sessions.filter(session => session.userId === parseInt(userId)).forEach(session => session.online = false);
        this._sessions.push(session);

        return response.json({ error: false, content: session });
    }

    delete(request, response) {
        const { id } = request.params;
        
        let session = null;
        for(const i of this._sessions) {
            if(this._sessions[i].id === id) {
                session = this._sessions[i];
                session.lastUpdate = Date.now();
                session.online = false;
                break;
            }
        }

        return response.json({ error: session === null, content: session });
    }

    getUserOnline(userId) {
        return this._sessions.filter(session => session.userId === parseInt(userId)).some(this._checkOnlineSession.bind(this));
    }

    _generateSessionId(userId) {
        const randomstring = require('randomstring');
        const random = randomstring.generate(20);
        const currentDate = new Date().toLocaleString().replace(/[^0-9]/gi, '');
        return userId + currentDate + random;
    }

    _checkOnlineSession(session) {
        if(session.online && Date.now() - session.lastUpdate > 10000) {
            session.online = false;
        }
        return session.online;
    }
};
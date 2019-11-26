const randomString = require('randomstring');
const sessionTimeout = 10000;

class Session {
    
    constructor(randomString, sessionTimeout) {
        this.sessions = [
            { id: 'abcd1', userId: 1, lastUpdate: Date.now(), online: true, url: '' },
            { id: 'abcd2', userId: 2, lastUpdate: Date.now(), online: true, url: '' },
            { id: 'abcd3', userId: 3, lastUpdate: Date.now(), online: true, url: '' },
            { id: 'abcd4', userId: 4, lastUpdate: Date.now(), online: true, url: '' },
            { id: 'abcd5', userId: 5, lastUpdate: Date.now(), online: true, url: '' }
        ];
        this.randomString = randomString;
        this.sessionTimeout = sessionTimeout;
    }

    persist(session) {
        const existingSession = this.getSessionById(session.id);
        if(!existingSession) {
            session.id = this.generateSessionId(session.userId);
            this.sessions.push(session);
        } else {
            Object.assign(existingSession, session);
        }
    }

    updateSession(userId) {
        const session = this.getOnlineSessionByUserId(userId);
        if(!session) {
            throw new Error('Authentication error.');
        }
        if(!this.checkOnlineSession(session)) {
            throw new Error('User is offline.');
        }
        session.lastUpdate = Date.now();
        this.persist(session);
    }

    getOnlineSessionByUserId(userId) {
        if(!userId) {
            return null;
        }
        userId = parseInt(userId);
        return this.sessions.find(session => session.online && session.userId === userId);
    }

    getSessionById(sessionId) {
        if(!sessionId) {
            return null;
        }
        return this.sessions.find(session => session.id === sessionId);
    }

    generateSessionId(userId) {
        return userId + '-' + new Date().toLocaleString().replace(/[^0-9]/gi, '') + '-' + this.randomString.generate(20);
    }

    checkOnlineSession(session) {
        if(session.online && Date.now() - session.lastUpdate > this.sessionTimeout) {
            session.online = false;
        }
        return session.online;
    }

    getOnlineAddress(userId) {
        const session = this.getOnlineSessionByUserId(userId);
        if(!session) {
            return null;
        }
        return session.url;
    }

}

module.exports = new Session(randomString, sessionTimeout);
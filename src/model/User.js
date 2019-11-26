class User {

    constructor() {
        this.users = [
            { id: 1, nickname: 'lucas',    email: 'lucas@email.com',    password: '1234', yearOfBirth: 10 },
            { id: 2, nickname: 'fusinato', email: 'fusinato@email.com', password: '1234', yearOfBirth: 20 },
            { id: 3, nickname: 'wilhelm',  email: 'wilhelm@email.com',  password: '1234', yearOfBirth: 30 },
            { id: 4, nickname: 'chiodini', email: 'chiodini@email.com', password: '1234', yearOfBirth: 40 },
            { id: 5, nickname: 'zanis',    email: 'zanis@email.com',    password: '1234', yearOfBirth: 50 }
        ];
    }

    persist(user) {
        const existingUser = this.getUserById(user.id);
        if(!existingUser) {
            user.id = this.users.length;
            this.users.push(user);
        }
        else {
            Object.assign(existingUser, user);
        }
    }

    getUserById(userId) {
        if(!userId) {
            return null;
        }
        userId = parseInt(userId);
        return this.users.find(user => user.id === userId);
    }

    getUserByNickname(userNickname) {
        if(!userNickname) {
            return null;
        }
        return this.users.find(user => user.nickname === userNickname);
    }

    getUserByEmail(userEmail) {
        if(!userEmail) {
            return null;
        }
        return this.users.find(user => user.email === userEmail);
    }

    authenticate(email, password) {
        const user = this.getUserByEmail(email);
        if(!user) {
            throw new Error('User email not found.');
        }
        if(user.password !== password) {
            throw new Error('Incorrect password.');
        }
        return user;
    }

}

module.exports = new User();
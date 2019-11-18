module.exports = new class {

    constructor() {
        this._users = [
            { id: 1, nickname: 'lucas',    email: 'lucas@email.com',    password: '1234', yearOfBirth: 10 },
            { id: 2, nickname: 'fusinato', email: 'fusinato@email.com', password: '1234', yearOfBirth: 20 },
            { id: 3, nickname: 'wilhelm',  email: 'wilhelm@email.com',  password: '1234', yearOfBirth: 30 },
            { id: 4, nickname: 'chiodini', email: 'chiodini@email.com', password: '1234', yearOfBirth: 40 },
            { id: 5, nickname: 'zanis',    email: 'zanis@email.com',    password: '1234', yearOfBirth: 50 }
        ];
    }

    listAll(request, response) {
        return response.json({ error: false, content: this._users.map(this._hidePassword) });
    }

    create(request, response) {
        const { nickname, email, password, yearOfBirth } = request.body;

        const user = { id : this._users.length, nickname, email, password, yearOfBirth };
        this._users.push(user);

        return response.json({ error: false, content: this._hidePassword(user) });
    }

    update(request, response) {
        const { id } = request.params;
        const { nickname, email, password, yearOfBirth } = request.body;

        const user = this._users.find(user => user.id === parseInt(id));
        if(!user) {
            return response.json({ error: true, content: 'User not found.' });
        }

        const userData = { nickname, email, password, yearOfBirth };
        Object.assign(user, userData);

        return response.json({ error: false, content: this._hidePassword(user) });
    }

    getUserNickname(userId) {
        return this._users.find(user => user.id === userId).nickname;
    }

    authenticate(email, password) {
        const user = this._users.find(user => user.email === email);
        if(!user) {
            throw new Error('User email not found.');
        }
        if(user.password !== password) {
            throw new Error('Incorrect password.');
        }
        return user;
    }

    _hidePassword(user) {
        user.password = user.password.replace(/./gi, '*');
        return user;
    }
};
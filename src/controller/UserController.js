const User = require('../model/User');
const Session = require('../model/Session');

class UserController {

    constructor(userModel, sessionModel) {
        this.userModel = userModel;
        this.sessionModel = sessionModel;
    }

    create(request, response) {
        const { nickname, email, password, yearOfBirth } = request.body;
        const responseData = { error: false, content: null };

        if(this.userModel.getUserByNickname(nickname)) {
            responseData.error = true;
            responseData.content = 'Nickname already registered.';
        }
        else if(this.userModel.getUserByEmail(email)) {
            responseData.error = true;
            responseData.content = 'Email already registered.';
        }
        
        if(!responseData.error) {
            const newUser = { nickname, email, password, yearOfBirth };
            this.userModel.persist(newUser);
            responseData.content = newUser;
        }

        return response.json(responseData);
    }

    update(request, response) {
        const { userId } = request.params;
        const { nickname, email, password, yearOfBirth } = request.body;
        const responseData = { error: false, content: null };
        
        try {
            this.sessionModel.updateSession(userId);
        
            const user = this.userModel.getUserById(userId);
            if(!user) {
                throw new Error('User not found.');
            }
            else if(user.nickname !== nickname && this.userModel.getUserByNickname(nickname)) {
                throw new Error('Nickname already registered.');
            }
            else if(user.email !== email && this.userModel.getUserByEmail(email)) {
                throw new Error('Email aready registered.');
            }
            
            Object.assign(user, { nickname, email, password, yearOfBirth });
            this.userModel.persist(user);
            responseData.content = user;
        } catch(error) {
            responseData.error = true;
            responseData.content = error.message;
        }

        return response.json(responseData);
    }

}

module.exports = new UserController(User, Session);
const express = require('express');

const SessionController = require('./controller/SessionController');
const UserController = require('./controller/UserController');
const ContactController = require('./controller/ContactController');

const routes = express.Router();

routes.post('/sessions', SessionController.create.bind(SessionController));
routes.delete('/sessions/:sessionId', SessionController.delete.bind(SessionController));

routes.post('/users', UserController.create.bind(UserController));
routes.put('/users/:userId', UserController.update.bind(UserController));

routes.get('/users/:userId/contacts', ContactController.listAll.bind(ContactController));
routes.post('/users/:userId/contacts', ContactController.create.bind(ContactController));
routes.delete('/users/:userId/contacts/:id', ContactController.delete.bind(ContactController));

module.exports = routes;
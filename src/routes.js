const express = require('express');

const SessionController = require('./controller/SessionController');
const UserController = require('./controller/UserController');
const ContactController = require('./controller/ContactController');

const routes = express.Router();

routes.post('/sessions', SessionController.create.bind(SessionController));
routes.delete('/sessions/:id', SessionController.delete.bind(SessionController));

routes.get('/users', UserController.listAll.bind(UserController));
routes.post('/users', UserController.create.bind(UserController));
routes.put('/users/:id', UserController.update.bind(UserController));

routes.get('/users/:userId/contacts', ContactController.listAll.bind(ContactController));
routes.post('/users/:userId/contacts', ContactController.create.bind(ContactController));
routes.delete('/users/:userId/contacts/:id', ContactController.delete.bind(ContactController));

module.exports = routes;
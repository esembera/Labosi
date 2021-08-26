const express = require('express');
const router = express.Router();
const User = require('../models/UserModel')


router.get('/', function (req, res, next) {
    //####################### ZADATAK #######################
    //vrati login stranicu

    res.render('login', {
        title: 'Login',
        user: req.session.user,
        linkActive: 'login',
        err: req.session.err
    });
    //#######################################################

});

router.post('/',async function (req, res, next) {
    //####################### ZADATAK #######################
    //postupak prijave korisnika

    
    let user = await User.fetchByUsername(req.body.user);

    
    if (!user.checkPassword(req.body.password) || user.id === undefined) {
        res.render('login', {
            title: 'Login',
            linkActive: 'login',
            user: req.session.user,
            err: 'User does not exist or incorrect password.',
        });
        return;
    }

    req.session.user = user;
    res.redirect('/');
    

    //#######################################################

});


module.exports = router;
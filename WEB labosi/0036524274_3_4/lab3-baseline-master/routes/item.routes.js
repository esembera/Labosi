var express = require('express');
var router = express.Router();
var db = require('../db');
const { route } = require('./order.routes')
const {check, body, validationResult } = require('express-validator');

router.get('/:id', async function(req, res, next) {
    var id = req.params.id;
    var item = await db.query(`select * from inventory where id=$1`,[id]);
    var trueItem=item.rows[0];
    var category = await db.query(`select * from categories where id=$1`,[trueItem.categoryid]);
    var suppliers = await db.query(`select * from suppliers where supplierfor=$1`,[id]);
    var TrueSuppliers = suppliers.rows;
    res.render('item', {
        title: trueItem.name,
        linkActive: 'order',
        item: trueItem,
        category: category,
        suppliers: TrueSuppliers
    });
});

router.get('/:id/editsupplier/:ids', async function(req, res, next) {
    var id = req.params.id;
    var ids = req.params.ids;
    var item= await db.query(`select * from inventory where id=$1`,[id]);
    var trueItem=item.rows[0];
    var supplier = await db.query(`select * from suppliers where id=$1`,[ids]);
    var TrueSupplier = supplier.rows[0];
    res.render('editsuppliers', {
        title: 'Edit supplier',
        linkActive: 'order',
        suppliers: TrueSupplier,
        item: trueItem
    });
});

router.post('/:id/editsupplier/:ids', [
    body("name").trim().isLength({
        min:2,
        max:22
    }),
    body("country").trim().isLength({
        min: 2,
        max: 22
    }),
    body("county").trim().isLength({
        min:2,
        max:22
    }),
    body("email").isEmail().normalizeEmail(),
    body("since").isInt({gt:1945, lt:2021})
],async function (req, res, next) {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
        res.render('error', {
            title: "Edit Supplier",
            errors: errors.array(),
            linkActive: 'order',
            itemID: req.params.id
        });
    } else {
            await db.query(`UPDATE suppliers SET name=$1, country = $2, county = $3, email= $4, suppliersince = $5 WHERE id = $6;`,
            [req.body.name, req.body.country, req.body.county, req.body.email, req.body.since, req.params.ids]);
            res.redirect("/item/"+ req.params.id);
            
        }
});
module.exports = router;
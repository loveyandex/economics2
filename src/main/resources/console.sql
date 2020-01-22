db.getCollection('stock').find({"substock":{time: 1179261000000, price: 4479}})

db.stock.find( {})

db.getCollection('stock').find({ "_id":17800036702302776,
    'subStock.time':{ $eq:1579165199000 } }
    ,{subStock:1, _id: 1})

db.getCollection('stock').
find({},{subStock: {$elemMatch: {time:{ $gte:1579078799000 }}}})

db.stock.aggregate(
    {$unwind: "$subStock"},
    {$match: {"subStock.time": { $gte:1579078799000 }}}
    )
db.dd.insertMany( [
    {
        _id: 1,
        zipcode: "63109",
        students: [
            { name: "john", school: 102, age: 10 },
            { name: "jess", school: 102, age: 11 },
            { name: "jeff", school: 108, age: 15 }
        ]
    },
{
    _id: 2,
    zipcode: "63110",
    students: [
        { name: "ajax", school: 100, age: 7 },
        { name: "achilles", school: 100, age: 8 },
    ]
},
{
    _id: 3,
        zipcode: "63109",
    students: [
    { name: "ajax", school: 100, age: 7 },
    { name: "achilles", school: 100, age: 8 },
]
},
{
    _id: 4,
        zipcode: "63109",
    students: [
    { name: "barney", school: 102, age: 7 },
    { name: "ruth", school: 102, age: 16 },
]
}

])


db.dd.find({},
    { students: { $elemMatch: { school: {$gte:102} } } } )
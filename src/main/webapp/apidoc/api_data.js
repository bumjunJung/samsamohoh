define({ "api": [
  {
    "type": "post",
    "url": "/familyAjax",
    "title": "",
    "version": "0.1.0",
    "name": "FamilyList",
    "group": "FamilyList",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchYear",
            "description": "<p>search year ('YYYY')</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchMonth",
            "description": "<p>search month ('MM')</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchDay",
            "description": "<p>search day ('DD')</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundFamilyList",
            "description": "<p>FamilyList was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n [{reg_date=YYYY-MM-DD, family_seq=Number, meal_type=(0,1,2,3), name=restaurants'name, cnt=Number},,,,{}]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/FamilyMgrController.java",
    "groupTitle": "FamilyList"
  },
  {
    "type": "get",
    "url": "/mo_login/:id",
    "title": "",
    "version": "0.1.0",
    "name": "UserLogin",
    "group": "Login",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>Users unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundUser",
            "description": "<p>The id of the User was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n {\"state\":\"true\"}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "UserNotFound",
            "description": "<p>The id of the User was not found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response",
          "content": "HTTP/1.1 4xx Not Found\n{\n {\"state\":\"false\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/MobileController.java",
    "groupTitle": "Login"
  },
  {
    "type": "post",
    "url": "/getChartData",
    "title": "[MemberData]",
    "version": "0.1.0",
    "name": "MemberData",
    "group": "MemberAnalysis",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>Search users ID.</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "meal_type",
            "description": "<p>Search Meal types</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "session_id",
            "description": "<p>Users session ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundMemberData",
            "description": "<p>MemberData was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n [xx.x, xx.x,,,,,, xx.x]\n or\n []\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/MemberAnalysisController.java",
    "groupTitle": "MemberAnalysis"
  },
  {
    "type": "post",
    "url": "/getChartLabel",
    "title": "[MemberLabel]",
    "version": "0.1.0",
    "name": "MemberLabel",
    "group": "MemberAnalysis",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>Search users ID.</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "meal_type",
            "description": "<p>Search Meal types</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "session_id",
            "description": "<p>Users session ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundMembersName",
            "description": "<p>Members Name was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n [{meal_type=(0,1,2,3), cnt=Number, name=Name, id=ID},,,,,{}]\n or\n []\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/MemberAnalysisController.java",
    "groupTitle": "MemberAnalysis"
  },
  {
    "type": "post",
    "url": "/getMemberName",
    "title": "[MemberName]",
    "version": "0.1.0",
    "name": "MemberName",
    "group": "MemberAnalysis",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>Users unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundMembersName",
            "description": "<p>Members Name was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n {\"code\":\"member's code\",\"grade\":\"user or admin\",\"id\":\"ID\"\"name\":\"Name\",\"reg_date\":\"date\",\"state\":\"Y or N\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/MemberAnalysisController.java",
    "groupTitle": "MemberAnalysis"
  },
  {
    "type": "post",
    "url": "/memberdelete",
    "title": "[MemberDelete]",
    "version": "0.1.0",
    "name": "MemberDelete",
    "group": "Member",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "memberCode",
            "description": "<p>Users unique CODE.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchNm",
            "description": "<p>search key word(id, name)</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "DeleteMember",
            "description": "<p>Member was delete.</p>"
          },
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundMemberList",
            "description": "<p>MemberList was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n {\"code\":\"members code\",\"grade\":\"user or admin\",\"id\":\"ID\"\"name\":\"Name\",\"reg_date\":\"date\",\"state\":\"Y or N\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/MemberMgrController.java",
    "groupTitle": "Member"
  },
  {
    "type": "post",
    "url": "/memberajax",
    "title": "[MemberList]",
    "version": "0.1.0",
    "name": "MemberList",
    "group": "Member",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchNm",
            "description": "<p>search key word(id, name)</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundMemberList",
            "description": "<p>MemberList was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n {\"code\":\"members code\",\"grade\":\"user or admin\",\"id\":\"ID\"\"name\":\"Name\",\"reg_date\":\"date\",\"state\":\"Y or N\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/MemberMgrController.java",
    "groupTitle": "Member"
  },
  {
    "type": "post",
    "url": "/getPriceData",
    "title": "[PriceData]",
    "version": "0.1.0",
    "name": "PriceData",
    "group": "PayAnalysis",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>Search users ID.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "meal_type",
            "description": "<p>Search Meal types</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchYear",
            "description": "<p>search year ('YYYY')</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchMonth",
            "description": "<p>search month ('MM')</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundPriceData",
            "description": "<p>PriceData was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n [{meal_type=(0,1,2,3), price=xx000, settling_date=date, id=ID},,,,{}]\n or\n []\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/PayMgrController.java",
    "groupTitle": "PayAnalysis"
  },
  {
    "type": "post",
    "url": "/getPriceLabel",
    "title": "[PriceLabel]",
    "version": "0.1.0",
    "name": "PriceLabel",
    "group": "PayAnalysis",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>Search users ID.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "meal_type",
            "description": "<p>Search Meal types</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchYear",
            "description": "<p>search year ('YYYY')</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchMonth",
            "description": "<p>search month ('MM')</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundPriceLabel",
            "description": "<p>Price Label was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n [점심, 저녁]\n or\n []\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/PayMgrController.java",
    "groupTitle": "PayAnalysis"
  },
  {
    "type": "post",
    "url": "/payAjax",
    "title": "[ReceiptList]",
    "version": "0.1.0",
    "name": "ReceiptList",
    "group": "Receipt",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchYear",
            "description": "<p>search year ('YYYY')</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchMonth",
            "description": "<p>search month ('MM')</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundReceiptList",
            "description": "<p>ReceiptList was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n [{total_price=xx000, meal_type=(0,1,2,3), etc=\"\", cnt=Number, settling_date=date, pay_seq=Number},,,,,,,{}]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/ReceiptMgrController.java",
    "groupTitle": "Receipt"
  },
  {
    "type": "post",
    "url": "/receiptModify",
    "title": "[ReceiptModify]",
    "version": "0.1.0",
    "name": "receiptModify",
    "group": "Receipt",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "seq",
            "description": "<p>receipts unique Number.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "mealType",
            "description": "<p>change mealTypes</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchYear",
            "description": "<p>search year ('YYYY')</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchMonth",
            "description": "<p>search month ('MM')</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "ModifyReceipt",
            "description": "<p>Receipt was modify</p>"
          },
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundReceiptList",
            "description": "<p>ReceiptList was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n [{total_price=xx000, meal_type=(0,1,2,3), etc=\"\", cnt=Number, settling_date=date, pay_seq=Number},,,,,,,{}]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/ReceiptMgrController.java",
    "groupTitle": "Receipt"
  },
  {
    "type": "post",
    "url": "/mo_addRestaurant",
    "title": "[AddRestaurant]",
    "version": "0.1.0",
    "name": "AddRestarurant",
    "group": "Restarurant",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "reg_id",
            "description": "<p>Users unique ID.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "restaurantName",
            "description": "<p>add restaurant naming</p>"
          },
          {
            "group": "Parameter",
            "type": "MultipartFile",
            "optional": false,
            "field": "restaurantImage",
            "description": "<p>restaurant use image</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "restaurantMenu",
            "description": "<p>restaurant menu</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "restaurantTag",
            "description": "<p>restaurant related tags</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "ExistRestaurant",
            "description": "<p>The restaurantsList was exist</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n {\"restaurant\":[{\"code\":\"2016-07-18_R1\",\"file\":\"경로&file.jpg\",\"name\":\"식당명\"}...{}]}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "RestaurantsNotFound",
            "description": "<p>The restaurant list was not found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response",
          "content": "HTTP/1.1 4xx Not Found\n{\n {\"restaurant\":\"null\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/MobileController.java",
    "groupTitle": "Restarurant"
  },
  {
    "type": "get",
    "url": "/mo_restaurnt/:id:searchKey",
    "title": "[restaurantList]",
    "version": "0.1.0",
    "name": "Main_RestarurantList",
    "group": "Restarurant",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>Users unique ID.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "searchKey",
            "description": "<p>search key word</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "ExistRestaurant",
            "description": "<p>The restaurantsList was exist</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n {\"restaurant\":[{\"code\":\"2016-07-18_R1\",\"file\":\"경로&file.jpg\",\"name\":\"식당명\"}...{}]}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "UserNotFound",
            "description": "<p>The id of the User was not found.</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "RestaurantsNotFound",
            "description": "<p>The restaurant list was not found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "UserNotFound",
          "content": "HTTP/1.1 4xx UserNotFound\n{\n {\"state\":\"false\"}\n}",
          "type": "json"
        },
        {
          "title": "RestaurantsNotFound",
          "content": "HTTP/1.1 4xx Not Found\n{\n {\"restaurant\":\"null\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/MobileController.java",
    "groupTitle": "Restarurant"
  },
  {
    "type": "post",
    "url": "/mo_modifyRestaurant",
    "title": "[ModifyRestaurant]",
    "version": "0.1.0",
    "name": "ModifyRestarurant",
    "group": "Restarurant",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "CODE",
            "description": "<p>Restaurnats unique CODE.</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "restaurantName",
            "description": "<p>add restaurant naming</p>"
          },
          {
            "group": "Parameter",
            "type": "MultipartFile",
            "optional": false,
            "field": "restaurantImage",
            "description": "<p>restaurant use image</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "originalImage",
            "description": "<p>imageFile original name</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "restaurantMenu",
            "description": "<p>restaurant menu</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "restaurantTag",
            "description": "<p>restaurant related tags</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "restaurantUse_Yn",
            "description": "<p>Whether operating a restaurant</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "update_id",
            "description": "<p>Users unique ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "ExistRestaurant",
            "description": "<p>The restaurantsList was exist</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n {\"restaurant\":[{\"code\":\"2016-07-18_R1\",\"file\":\"경로&file.jpg\",\"name\":\"식당명\"}...{}]}\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "RestaurantsNotFound",
            "description": "<p>The restaurant list was not found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response",
          "content": "HTTP/1.1 4xx Not Found\n{\n {\"restaurant\":\"null\"}\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/MobileController.java",
    "groupTitle": "Restarurant"
  },
  {
    "type": "post",
    "url": "/getRChartData",
    "title": "[RestaurantsData]",
    "version": "0.1.0",
    "name": "RestaurantsData",
    "group": "RestaurantAnalysis",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>Search users ID.</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "meal_type",
            "description": "<p>Search Meal types</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "session_id",
            "description": "<p>Users session ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "grade",
            "description": "<p>Users GRADE.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundRestaurantsData",
            "description": "<p>RestaurantsData was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n [xx.x, xx.x,,,,,, xx.x]\n or\n []\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/RestaurantAnalysisController.java",
    "groupTitle": "RestaurantAnalysis"
  },
  {
    "type": "post",
    "url": "/getRchartLabel",
    "title": "[RestaurantsLabel]",
    "version": "0.1.0",
    "name": "RestaurantsLabel",
    "group": "RestaurantAnalysis",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "id",
            "description": "<p>Search users ID.</p>"
          },
          {
            "group": "Parameter",
            "type": "int",
            "optional": false,
            "field": "meal_type",
            "description": "<p>Search Meal types</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "session_id",
            "description": "<p>Users session ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "grade",
            "description": "<p>Users GRADE.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "optional": false,
            "field": "FoundRestaurantsLabel",
            "description": "<p>RestaurantsLabel was found.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response",
          "content": "HTTP/1.1 200 OK\n{\n [{meal_type=(0,1,2,3), name=RestaurantName, cnt=Number, id=ID},,,,{}]\n or\n []\n}",
          "type": "json"
        }
      ]
    },
    "filename": "C:/Users/LeeYura/git/samsamohoh_web/samsamohoh/src/main/java/ex2i/kr/samsamohoh/controller/RestaurantAnalysisController.java",
    "groupTitle": "RestaurantAnalysis"
  }
] });

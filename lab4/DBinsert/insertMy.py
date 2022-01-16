 
import psycopg2
import random

def insert_vendor(name,price,amount,brand,manufacturer,
    subcategory_id):
    """ insert a new vendor into the vendors table """
    sql = """insert into good (name,price,amount,brand,manufacturer,
    discount,subcategory_id) values(%s,%s,%s,%s,%s,0,%s) RETURNING id;"""

    vendor_id = None
    try:
        conn = psycopg2.connect(
        host="localhost",
        database="esport21",
        user="postgres",
        password="1")

        # create a new cursor
        cur = conn.cursor()
        # execute the INSERT statement
        cur.execute(sql, (name, price,amount,brand,manufacturer,
    subcategory_id))
        # get the generated id back
        vendor_id = cur.fetchone()[0]
        # commit the changes to the database
        conn.commit()
        # close communication with the database
        cur.close()
    except (Exception, psycopg2.DatabaseError) as error:
        print(error)
    finally:
        if conn is not None:
            conn.close()

    return vendor_id

if __name__ == '__main__':
    # insert one vendor
    name = ""

    for i in range(10,100020):
        name = "назва товару" + str(i)
        price = random.randint(1,2000)
        amount = random.randint(1,2000)
        brand = 'бренд' + str(i)
        manufacturer = 'виробник' + str(i)
        subcategory_id = random.randint(1,9)
        insert_vendor(name, price,amount,brand,manufacturer,
    subcategory_id)
    # insert multiple vendors

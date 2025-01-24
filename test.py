import os
data=""
for file_path in os.listdir("E:/Logiciels utiles/PasswordSniffer/Data/"):
    if(file_path.endswith(".txt")):
        f = open("E:/Logiciels utiles/PasswordSniffer/Data/"+file_path, "r")
        for x in f:
            if x.startswith('URL               : '):
                if x.startswith('URL               : https://www.tf1.fr'):
                    data = data + "TV;"
                elif x.startswith('URL               : https://www.sfr.fr'):
                    data = data + "INTERNET;"
                elif x.startswith('URL               : https://assure.ameli.fr'):
                    data = data + "AMELI;"
                elif x.startswith('URL               : https://fr-fr.facebook.com'):
                    data = data + "SOCIAL;"
                elif x.startswith('URL               : https://login.live.com'):
                    data = data + "MAIL;"
                elif x.startswith('URL               : free.moncomptefree'):
                    data = data + "INTERNET;"
                elif x.startswith('URL               : allego.carrefour'):
                    data = data + "MAGASIN;"
                elif x.startswith('URL               : sfr'):
                    data = data + "INTERNET;"
                elif x.startswith('URL               : appelmedical'):
                    data = data + "SANTE;"
                elif x.startswith('URL               : facebook.katana'):
                    data = data + "SOCIAL;"
                elif x.startswith('URL               : http://imp.free.fr'):
                    data = data + "INTERNET;"
                elif x.startswith('URL               : https://auth-nie.natixis.com'):
                    data = data + "BANQUE;"
                elif x.startswith('URL               : https://b2ceurwwprdidp.b2clogin.com'):
                    data = data + "BANQUE;"
                elif x.startswith('URL               : https://cfspart-idp.impots.gouv.fr'):
                    data = data + "IMPOTS;"
                elif x.startswith('URL               : https://eer.hellobank.fr'):
                    data = data + "BANQUE;"
                elif x.startswith('URL               : https://faphouse.com/'):
                    data = data + "SOCIAL+;"
                elif x.startswith('URL               : https://fc.assure.ameli.fr/FRCO-app/login'):
                    data = data + "SANTE;"
                elif x.startswith('URL               : https://idp.impots.gouv.fr'):
                    data = data + "IMPOTS;"
                elif x.startswith('URL               : https://market.rbc-placementsdirect.com/login'):
                    data = data + "BANQUE;"
                elif x.startswith('URL               : https://mobile.free.fr'):
                    data = data + "INTERNET;"
                elif x.startswith('URL               : https://moncompte.laposte.fr'):
                    data = data + "BANQUE;"
                elif x.startswith('URL               : https://ohnaturist.com'):
                    data = data + "SOCIAL+;"
                elif x.startswith('URL               : https://profile.gulli.fr'):
                    data = data + "TV;"
                elif x.startswith('URL               : https://souscrire.boursorama.com'):
                    data = data + "BANQUE;"
                elif x.startswith('URL               : https://subscribe.free.fr'):
                    data = data + "INTERNET;"
                elif x.startswith('URL               : https://www.cam4.fr'):
                    data = data + "SOCIAL+;"
                elif x.startswith('URL               : https://pass.canalplus.com'):
                    data = data + "TV+;"
                else:
                    data = data + "AUTRE;"
                data = data + x[20:-1]+";"
            if x.startswith('User Name         : '):
                data = data + x[20:-1]+";"
            if x.startswith('Network Name (SSID): '):
                data = data + "WIFI;WIFI;"+ x[21:-1]+";"
            if x.startswith('Password          : '):
                data = data + x[20:-1]+"\n"
            if x.startswith('Key (Ascii)       : '):
                data = data + x[20:-1]+"\n"
             
f = open("demofile2.txt", "a")
f.write(data)
f.close()

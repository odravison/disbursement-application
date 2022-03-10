INSERT INTO public.merchant (id,deleted,email,"name",cif) VALUES
              (1,false,'info@flatley-rowe.com','Flatley-Rowe','B611111111'),
              (2,false,'info@weissnat-hackett-and-purdy.com','Weissnat, Hackett and Purdy','B611111112'),
              (3,false,'info@streich-klocko-and-marvin.com','Streich, Klocko and Marvin','B611111113'),
              (4,false,'info@pfeffer-wiza-and-jacobson.com','Pfeffer, Wiza and Jacobson','B611111114'),
              (5,false,'info@von-and-sons.com','Von and Sons','B611111115'),
              (6,false,'info@oga-inc.com','Oga Inc','B611111116'),
              (7,false,'info@mayer-kemmer-and-schumm.com','Mayer, Kemmer and Schumm','B611111117'),
              (8,false,'info@zulauf-roberts.com','Zulauf-Roberts','B611111118'),
              (9,false,'info@towne-waelchi.com','Towne-Waelchi','B611111119'),
              (10,false,'info@schoen-inc.com','Schoen Inc','B611111110');
INSERT INTO public.merchant (id,deleted,email,"name",cif) VALUES
              (11,false,'info@dietrich-ortiz.com','Dietrich-Ortiz','B611111111'),
              (12,false,'info@lubowitz-hessel-and-berge.com','Lubowitz, Hessel and Berge','B611111112'),
              (13,false,'info@streich-koepp.com','Streich-Koepp','B611111113'),
              (14,false,'info@hodkiewicz-stehr.com','Hodkiewicz-Stehr','B611111114');

ALTER SEQUENCE merchant_seq RESTART WITH 15;
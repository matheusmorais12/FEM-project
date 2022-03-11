# FEM project
 Computer Science - 
Numerical Methods and Tools - Finite Element Method: presentation and 2D implementation

Développer un logiciel basé sur la méthode des éléments finis pour calculer le maximum tension qui peut être appliquée à l'isolant solide pour éviter le 
claquage diélectrique.

![image](https://user-images.githubusercontent.com/50896072/157978444-84154e51-4eed-487d-abcf-f1d6c89d1479.png)

Dans l'analyse par éléments finis, nous approximons une fonction définie dans un domaine D avec un ensemble de fonctions de base orthogonales avec des coefficients correspondant aux valeurs fonctionnelles à certains points de nœud. La solution des valeurs aux nœuds pour certaines équations aux dérivées partielles peut être obtenue en résolvant un système linéaire d'équations impliquant l'inversion de matrices (parfois éparses). Les conditions aux limites sont intrinsèquement satisfaites avec cette formulation qui est l'un des avantages par rapport aux différences finies.

1. Alors, il fallait suivre les étapes ci-dessous :

  a. Décomposer (mailler) le domaine étudié en plusieurs sous domaines (éléments finis). Les éléments récupèrent le domaine entier sans chevauchement entre eux.

  b. Dans chaque élément, la variable d'état est calculée aux nœuds n1, n2 et n3, et interpolée entre eux

![image](https://user-images.githubusercontent.com/50896072/157979034-6534eaa4-ebeb-4c14-9838-f7947566a480.png)

  c.La solution aux nœuds est donnée par une expression matricielle [A] [x] = [B]

  d. La solution complète est une interpolation par des fonctions linéaires (ou quadratiques) définies dans chaque élément

![image](https://user-images.githubusercontent.com/50896072/157979729-9c5a2793-9c06-4660-9cb0-1f348d80fa50.png)

Où Δ est égal à deux fois l'aire de l'élément. Son expression est donnée par :

![image](https://user-images.githubusercontent.com/50896072/157979857-a13119ae-06c7-47c7-a218-e7f045e39364.png)

Par construction, les fonctions de forme sont continuées, sur le domaine décrétiste, et différentiables sur chaque élément fini. Le calcul des termes mij dans l'équation du système d'équations à suivant, est obtenu en effectuant la somme des intégrales obtenues sur chaque élément, en tenant compte du rapport entre les sommets des éléments et les valeurs des indices i et j. Si l'on prend, sur l'exemple étudié, les termes mij utilisés sous forme de somme d'intégrales appliquées à chaque élément du maillage. Pour les éléments qui ne contiennent pas les nœuds i et j cette intégrale est nulle. On peut écrire ce terme comme une somme de termes élémentaires :

![image](https://user-images.githubusercontent.com/50896072/157980176-f99893f6-3809-464e-9ad7-e585d2b7a514.png)

L’algorithme de construction de l’équation du système d’équations consiste donc à prendre tous les sommets libres un à un et à calculer les termes mij pour chaque élément fini qui ont les nœuds i et j comme sommets. Si l’on numérote les éléments de la figure 1 de 0 à 8 et, au sein de chaque élément, les nœuds sont numérotés au sens trigonométrique (voir figure 2) l’équation 3 montre la matrice globale M obtenue à partir de l'algorithme. Dans ce cas sa taille est de 3 x 3 car nous avons 3 nœuds libres (l'autre appartient à la frontière dans laquelle les valeurs de V sont connues - condition de Dirichlet bounadry).

![image](https://user-images.githubusercontent.com/50896072/157980266-dac3bd08-d963-4149-a212-27f224004d99.png)


Alors, après les connaissances précédentes sur les formulations nécessaire pour développer un logiciel basé sur la méthode des éléments finis pour calculer le maximum tension qui peut être appliquée à l'isolant solide pour éviter le claquage diélectrique, il a été réalisé l’implémentation sur le NetBeans et le langage de programation utilisée était du Java et certaines classes seront discutées dans l’annexe à la fin du rapport.

2D FEM implementation: The studied problem.
1. Using the developed finite element method:

![image](https://user-images.githubusercontent.com/50896072/157980377-10e72923-91d1-4436-859d-b16e224b85a0.png)

![image](https://user-images.githubusercontent.com/50896072/157980410-7c652d35-fa6b-44d8-8e42-2cd15fcbca3f.png)

a. Compute and plot the electric potential distribution on studied domain:

Figure 1 – Electric Potential Distribution (sans la bulle d'air)

![image](https://user-images.githubusercontent.com/50896072/157980479-f6530b94-1b58-46c8-9368-9977719eb1f1.png)

Figure 2 – Electric Potential Distribution (avec la bulle d'air)

![image](https://user-images.githubusercontent.com/50896072/157980593-a94b3504-4be2-4e0d-a727-0df0acf9b4cf.png)

Determine the maximum voltage that can be applied to the solid insulator to avoid breakdown 1) without the presence of the air bubble and then 2) in its presence:

La rigidité diélectrique de la bulle d'air est inférieure à celle de l'isolant solide (3kV/mm vs 20 kV/mm). Donc, pour le premier parti (sans la présence de la bulle d’air), on peut augmenter la tension jusqu’à la rigidité diélectrique atteigne 20KV/mm.

![image](https://user-images.githubusercontent.com/50896072/157980892-600535b9-63e7-4ba5-a184-05637204547a.png)

Alors, comme maintenant il y a la bulle d’air, Il faut augmenter la tension border output jusqu'à que E > 3 kV /mm.

![image](https://user-images.githubusercontent.com/50896072/157980995-abe931d9-b928-4f1a-850d-0d1baff69e17.png)

c. Determine the maximum voltage that can be applied to the structure if instead of epoxy resin with 60% alumina, we use (1) PVDF with a relative permittivity of 8.4 at 50Hz and dielectric strength of 20kV/mm 

(2) Kapton with a relative permittivity of 3.4 et 50Hz and dielectric strength of 20kV/mm. Conclude on this study.

(1) PVDF with a relative permittivity of 8.4 at 50Hz and dielectric strength of 20kV/mm 

![image](https://user-images.githubusercontent.com/50896072/157981210-13204a3e-29ac-48f3-be79-9c34e48abef4.png)

(2) Kapton with a relative permittivity of 3.4 et 50Hz and dielectric strength of 20kV/mm. Conclude on this study. 

![image](https://user-images.githubusercontent.com/50896072/157981396-dacfb072-8892-490f-8043-d366eeff32f0.png)




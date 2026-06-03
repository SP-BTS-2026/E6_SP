<link rel="stylesheet" href="Style/style_appartement.css">

<div class="container">

    <div class="page-header" style="margin-bottom:30px;">
        <div>
            <h1><i class="fas fa-skiing"></i> Location de Matériel</h1>
            <p style="color:#777;">Équipez-vous avec le meilleur matériel pour vos sorties en montagne.</p>
        </div>
    </div>

    <?php if(isset($_GET['success'])): ?>
        <div style="background:#d4edda; color:#155724; padding:12px 16px; border-radius:8px; margin-bottom:20px;">
            <i class="fas fa-check-circle"></i> Matériel loué avec succès !
        </div>
    <?php endif; ?>

    <div class="apparts-grid">
        <?php if(!empty($lesMateriels)): ?>
            <?php foreach($lesMateriels as $unMateriel): ?>
                <article class="appart-card">
                    <div class="appart-image">
                        <span class="price">
                            <strong><?= number_format($unMateriel['prix_jour'], 2, ',', ' ') ?> €</strong> / jour
                        </span>
                        <img src="images/materiels/<?= htmlspecialchars($unMateriel['type_mat']) ?>.jpg"
                             alt="<?= htmlspecialchars($unMateriel['libelle_mat']) ?>"
                             onerror="this.src='images/background-montagne.jpg'">
                    </div>

                    <div class="appart-details">
                        <h3><?= htmlspecialchars($unMateriel['libelle_mat']) ?></h3>
                        <p style="color:#777;"><i class="fas fa-tag"></i> <?= htmlspecialchars($unMateriel['type_mat']) ?></p>
                        <p style="color:#27ae60;"><i class="fas fa-check"></i> État : <?= htmlspecialchars($unMateriel['etat']) ?></p>

                        <?php if(isset($_SESSION['id_user']) && $_SESSION['role'] == 'client'): ?>
                            <form method="POST" action="index.php?page=louer_materiel" style="margin-top:15px;">
                                <input type="hidden" name="id_mat" value="<?= $unMateriel['id_mat'] ?>">
                                <div style="margin-bottom:10px;">
                                    <label style="font-size:0.85rem; color:#555;">Date de début</label>
                                    <input type="date" name="date_debut" required min="<?= date('Y-m-d') ?>"
                                           style="width:100%; padding:8px; border:1px solid #ddd; border-radius:6px;">
                                </div>
                                <div style="margin-bottom:10px;">
                                    <label style="font-size:0.85rem; color:#555;">Date de fin</label>
                                    <input type="date" name="date_fin" required min="<?= date('Y-m-d') ?>"
                                           style="width:100%; padding:8px; border:1px solid #ddd; border-radius:6px;">
                                </div>
                                <button type="submit" name="btnLouer" class="btn-view" style="width:100%;">
                                    <i class="fas fa-shopping-cart"></i> Louer
                                </button>
                            </form>
                        <?php elseif(isset($_SESSION['role']) && $_SESSION['role'] == 'proprietaire'): ?>
                            <p style="color:#e74c3c; margin-top:15px; font-size:0.85rem;">
                                <i class="fas fa-info-circle"></i> Les propriétaires ne peuvent pas louer.
                            </p>
                        <?php else: ?>
                            <a href="index.php?page=connexion" class="btn-view" style="display:block; text-align:center; margin-top:15px;">
                                <i class="fas fa-lock"></i> Connectez-vous pour louer
                            </a>
                        <?php endif; ?>
                    </div>
                </article>
            <?php endforeach; ?>
        <?php else: ?>
            <p style="color:#777; text-align:center; grid-column:1/-1; padding:40px;">
                Aucun matériel disponible pour le moment.
            </p>
        <?php endif; ?>
    </div>
</div>
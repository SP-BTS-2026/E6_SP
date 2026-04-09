<link rel="stylesheet" href="Style/style_mes_apparts.css">

<div class="container form-appart-container">

    <a href="index.php?page=mes_apparts" class="btn-back">
        <i class="fas fa-arrow-left"></i> Retour à mes appartements
    </a>

    <?php if(!$unAppart): ?>
        <p style="color:#8a9bb0;text-align:center;padding:60px;">Appartement introuvable.</p>
    <?php else: ?>

    <div class="form-card">
        <h1><i class="fas fa-pen"></i> Modifier l'appartement</h1>

        <?php if(isset($erreur)): ?>
            <div class="alert-error"><i class="fas fa-exclamation-circle"></i> <?= $erreur ?></div>
        <?php endif; ?>

        <form method="POST" action="index.php?page=modif_appart&id=<?= $unAppart['id_appart'] ?>">
            <div class="form-grid">
                <div class="form-group">
                    <label>Numéro d'appartement</label>
                    <input type="text" name="num_appart" required value="<?= htmlspecialchars($unAppart['num_appart']) ?>">
                </div>
                <div class="form-group">
                    <label>Type</label>
                    <select name="type_appart" required>
                        <?php foreach(['Studio','T2','T3','T4','Chalet','Duplex'] as $type): ?>
                            <option value="<?= $type ?>" <?= $unAppart['type_appart'] == $type ? 'selected' : '' ?>>
                                <?= $type ?>
                            </option>
                        <?php endforeach; ?>
                    </select>
                </div>
                <div class="form-group">
                    <label>Surface (m²)</label>
                    <input type="number" name="surface" step="0.01" required value="<?= $unAppart['surface'] ?>">
                </div>
                <div class="form-group">
                    <label>Capacité d'accueil</label>
                    <input type="number" name="capacite_accueil" required value="<?= $unAppart['capacite_accueil'] ?>">
                </div>
                <div class="form-group">
                    <label>Exposition</label>
                    <select name="exposition">
                        <?php foreach(['Nord','Sud','Est','Ouest','Sud-Est','Sud-Ouest'] as $expo): ?>
                            <option value="<?= $expo ?>" <?= $unAppart['exposition'] == $expo ? 'selected' : '' ?>>
                                <?= $expo ?>
                            </option>
                        <?php endforeach; ?>
                    </select>
                </div>
                <div class="form-group">
                    <label>Distance pistes (m)</label>
                    <input type="number" name="distance_pistes" value="<?= $unAppart['distance_pistes'] ?>">
                </div>
                <div class="form-group">
                    <label>Prix hebdomadaire (€)</label>
                    <input type="number" name="prix_hebdo" step="0.01" required value="<?= $unAppart['prix_hebdo'] ?>">
                </div>
                <div class="form-group">
                    <label>Nom de l'image</label>
                    <input type="text" name="image" value="<?= htmlspecialchars($unAppart['image']) ?>">
                </div>
            </div>

            <button type="submit" name="btnModif" class="btn-submit">
                <i class="fas fa-save"></i> Enregistrer les modifications
            </button>
        </form>
    </div>
    <?php endif; ?>
</div>
